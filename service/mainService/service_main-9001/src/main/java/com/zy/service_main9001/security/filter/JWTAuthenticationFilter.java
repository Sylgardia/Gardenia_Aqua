package com.zy.service_main9001.security.filter;

import com.zy.service_main9001.security.beans.LoginUserDetails;
import com.zy.service_main9001.security.config.JWTConfig;
import com.zy.service_main9001.utils.AccessAddressUtils;
import com.zy.service_main9001.utils.JWTTokenUtils;
import com.zy.service_main9001.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangyu
 * @description jwt权限过滤器
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 取出Token
		String token = request.getHeader(JWTConfig.tokenHeader);

		if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
			// 是否在黑名单中
			if (JWTTokenUtils.isBlackList(token)) {
				ResponseUtils.responseJson(response, ResponseUtils.response(505, "Token已失效", "Token已进入黑名单"));
				return;
			}

			// 是否存在于Redis中
			if (JWTTokenUtils.hasToken(token)) {
				String ip = AccessAddressUtils.getIpAddress(request);
				String expiration = JWTTokenUtils.getExpirationByToken(token);
				String username = JWTTokenUtils.getUserNameByToken(token);

				// 判断是否过期
				if (JWTTokenUtils.isExpiration(expiration)) {
					// 加入黑名单
					JWTTokenUtils.addBlackList(token);

					// 是否在刷新期内
					String validTime = JWTTokenUtils.getRefreshTimeByToken(token);
					if (JWTTokenUtils.isValid(validTime)) {
						// 刷新Token，重新存入请求头
						String newToke = JWTTokenUtils.refreshAccessToken(token);

						// 删除旧的Token，并保存新的Token
						JWTTokenUtils.deleteRedisToken(token);
						JWTTokenUtils.setTokenInfo(newToke, username, ip);
						response.setHeader(JWTConfig.tokenHeader, newToke);

						log.info("用户{}的Token已过期，但为超过刷新时间，已刷新", username);

						token = newToke;
					} else {
						log.info("用户{}的Token已过期且超过刷新时间，不予刷新", username);

						// 加入黑名单
						JWTTokenUtils.addBlackList(token);
						ResponseUtils.responseJson(response, ResponseUtils.response(505, "Token已过期", "已超过刷新有效期"));
						return;
					}
				}

				LoginUserDetails consumerDetails = JWTTokenUtils.parseAccessToken(token);

				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(
								consumerDetails,
								consumerDetails.getId(),
								consumerDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
