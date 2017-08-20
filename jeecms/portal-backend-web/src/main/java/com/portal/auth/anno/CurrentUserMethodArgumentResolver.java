package com.portal.auth.anno;

import com.portal.auth.authdb.entity.Users;
import com.portal.auth.authdb.mapper.UsersMapper;
import com.portal.auth.shiro.utils.SystemAdminConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *

 <mvc:argument-resolvers>
 <bean class="com.portal.auth.anno.CurrentUserMethodArgumentResolver" autowire="byType"/>
 </mvc:argument-resolvers>
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {


    private UsersMapper usersMapper;
    public CurrentUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (null==SecurityUtils.getSubject() && null ==SecurityUtils.getSubject().getPrincipal()&&  StringUtils.isBlank(SecurityUtils.getSubject().getPrincipal().toString())) {
            return null;
        }
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        Users p1 = new Users();
        String loginedUserName = (String) SecurityUtils.getSubject().getPrincipal();

        if (StringUtils.isBlank(loginedUserName)) {
            return null;
        }
        /**
         * 返回一个虚拟的管理员账号
         */
        if (loginedUserName!=null && loginedUserName.equals(SystemAdminConfig.userName)) {
            Users users = new Users();
            users.setId(SystemAdminConfig.userName);
            users.setPwd(SystemAdminConfig.pwd_enc);
            users.setLoginname(SystemAdminConfig.userName);
            users.setName(SystemAdminConfig.userName);
            return users;
        }

        p1.setLoginname(loginedUserName);
        Users user = usersMapper.selectOne(p1);

        return user;
    }


    public UsersMapper getUsersMapper() {
        return usersMapper;
    }

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }
}
