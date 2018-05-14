package com.brendev.shopapp.api.shiro;

import com.brendev.shopapp.api.dao.ProfilRoleDaoBeanLocal;
import com.brendev.shopapp.api.dao.ProfilUtilisateurDaoBeanLocal;
import com.brendev.shopapp.api.dao.UtilisateurDaoBeanLocal;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilUtilisateur;
import com.brendev.shopapp.api.entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import javax.ejb.EJB;

public class EntityRealm extends AuthorizingRealm {

    @EJB
    private ProfilUtilisateurDaoBeanLocal pudbl;
    private UtilisateurDaoBeanLocal udbl;

    private ProfilRoleDaoBeanLocal prdbl;

    private List<ProfilUtilisateur> profilUtilisateurs;
    private static ProfilUtilisateur profilUtilisateur;
    private static List<Utilisateur> utilisateurs;
    private static Utilisateur utilisateur;

    private static Profil profil;
    private static List<ProfilRole> profilRoles;

    public EntityRealm() throws NamingException {
        this.profilUtilisateurs = new ArrayList<>();
        System.out.println("enter entity realm");
        this.setName("entityRealm");
        CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-256");
        this.setCredentialsMatcher(credentialsMatcher);
        InitialContext context = new InitialContext();
        //La classe session bean de l'utilisateur(précise la classe du sesion bean)
        this.pudbl = (ProfilUtilisateurDaoBeanLocal) context.lookup("java:global/SuperShop/ProfilUtilisateurDaoBean");
        this.udbl = (UtilisateurDaoBeanLocal) context.lookup("java:global/SuperShop/UtilisateurDaoBean");
        //La classe session bean des roles par profil(précise la classe du sesion bean)
        this.prdbl = (ProfilRoleDaoBeanLocal) context.lookup("java:global/SuperShop/ProfilRoleDaoBean");
        System.out.println("out entity realm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        setUtilisateur(getUdbl().getOneBy("login", token.getUsername()));

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        try {
            if (getUtilisateur() != null) {

                simpleAuthenticationInfo = new SimpleAuthenticationInfo(getUtilisateur().getLogin(), getUtilisateur().getMotPasse(), getName());

            } else {
                simpleAuthenticationInfo = null;
                throw new UnknownAccountException("Utilisateur inconnu");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //????????????????????????????????????????
        String userId = (String) principals.fromRealm(this.getName()).iterator().next();
        //Dans les guillemets on met le nom dansla base de donnée, ensuite on met la valeur
        setUtilisateur(getUdbl().getBy("login", userId).get(0));
        if (getUtilisateur() != null) {
            final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            setProfilUtilisateurs(this.getPudbl().getBy("utilisateur", getUtilisateur()));
            if (!profilUtilisateurs.isEmpty()) {
                for (ProfilUtilisateur pu : getProfilUtilisateurs()) {
                    if (pu.getDateRevocation() == null) {
                        setProfilRoles(this.getPrdbl().getBy("profil", pu.getProfil()));
                    }
                }

            }

            final List<String> roles = new ArrayList<>();
            for (ProfilRole proRole : getProfilRoles()) {
                roles.add(proRole.getRole().getNom());
            }
            info.addRoles(roles);

            return info;
        } else {
            return null;
        }
    }

    public static Utilisateur getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return getUtilisateur();
        }
        return null;
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * @return the pudbl
     */
    public ProfilUtilisateurDaoBeanLocal getPudbl() {
        return pudbl;
    }

    /**
     * @param pudbl the pudbl to set
     */
    public void setPudbl(ProfilUtilisateurDaoBeanLocal pudbl) {
        this.pudbl = pudbl;
    }

    /**
     * @return the udbl
     */
    public UtilisateurDaoBeanLocal getUdbl() {
        return udbl;
    }

    /**
     * @param udbl the udbl to set
     */
    public void setUdbl(UtilisateurDaoBeanLocal udbl) {
        this.udbl = udbl;
    }

    /**
     * @return the prdbl
     */
    public ProfilRoleDaoBeanLocal getPrdbl() {
        return prdbl;
    }

    /**
     * @param prdbl the prdbl to set
     */
    public void setPrdbl(ProfilRoleDaoBeanLocal prdbl) {
        this.prdbl = prdbl;
    }

    /**
     * @return the profilUtilisateurs
     */
    public List<ProfilUtilisateur> getProfilUtilisateurs() {
        return profilUtilisateurs;
    }

    /**
     * @param profilUtilisateurs the profilUtilisateurs to set
     */
    public void setProfilUtilisateurs(List<ProfilUtilisateur> profilUtilisateurs) {
        this.profilUtilisateurs = profilUtilisateurs;
    }

    /**
     * @return the profilUtilisateur
     */
    public static ProfilUtilisateur getProfilUtilisateur() {
        return profilUtilisateur;
    }

    /**
     * @param aProfilUtilisateur the profilUtilisateur to set
     */
    public static void setProfilUtilisateur(ProfilUtilisateur aProfilUtilisateur) {
        profilUtilisateur = aProfilUtilisateur;
    }

    /**
     * @return the utilisateurs
     */
    public static List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    /**
     * @param aUtilisateurs the utilisateurs to set
     */
    public static void setUtilisateurs(List<Utilisateur> aUtilisateurs) {
        utilisateurs = aUtilisateurs;
    }

    /**
     * @return the utilisateur
     */
    public static Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * @param aUtilisateur the utilisateur to set
     */
    public static void setUtilisateur(Utilisateur aUtilisateur) {
        utilisateur = aUtilisateur;
    }

    /**
     * @return the profil
     */
    public static Profil getProfil() {
        return profil;
    }

    /**
     * @param aProfil the profil to set
     */
    public static void setProfil(Profil aProfil) {
        profil = aProfil;
    }

    /**
     * @return the profilRoles
     */
    public static List<ProfilRole> getProfilRoles() {
        return profilRoles;
    }

    /**
     * @param aProfilRoles the profilRoles to set
     */
    public static void setProfilRoles(List<ProfilRole> aProfilRoles) {
        profilRoles = aProfilRoles;
    }

}
