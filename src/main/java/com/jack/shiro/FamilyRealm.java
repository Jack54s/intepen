package com.jack.shiro;

/**
 * Created by 11407 on 3/003.
 */
/*@Repository
public class FamilyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FamilyService familyService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        HashSet<String> roles = new HashSet<>();
        roles.add("family");
        String account = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(familyService.getPermissions(account));
        return authorizationInfo;
    }

    /**
     * 用于认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
/*    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account;
        String password;
        if(token != null) {
            if(token.getPrincipal() != null){
                account = token.getPrincipal().toString();
            }
            else{
                throw new AuthenticationException("账号为空！");
            }
            if(token.getCredentials() != null){
                password = new String((char[]) token.getCredentials());
            }
            else{
                throw new AuthenticationException("密码为空！");
            }
        }
        else{
            throw new AuthenticationException("token为空！");
        }

        Family family = familyService.getFamilyByAccount(account);

        if(family == null){
            throw new UnknownAccountException("无效的用户名！");
        }
        else{

            if(password.equals(family.getPassword())){
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account, password, getName());
                return info;
            }
            else{
                throw new IncorrectCredentialsException("密码错误！");
            }
        }
    }
}*/
