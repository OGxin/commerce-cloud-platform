package com.ozx.ozxshopbasicsspringcloudgateway.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;

import java.util.ArrayList;
import java.util.List;



/**
 * @ClassName: SwaggerProvider
 * @Description: 聚合接口文档注册
 * @Author ou.zhenxing
 * @Date 2020/3/9 17:03
 * @Version： 1.0
**/
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
	public static final String API_URI = "/v2/api-docs";
	private final RouteLocator routeLocator;
	private final GatewayProperties gatewayProperties;


	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<String> routes = new ArrayList<>();
		/**
		 * 获取Gateway配置文件的route参数
		 */
		routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
		/**
		 * 结合配置的route-path和route过滤，只获取有效的route节点
		 */
		gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
				.forEach(routeDefinition -> routeDefinition.getPredicates().stream()
						.filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
						.forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
								predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
										.replace("/**", API_URI)))));

		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
}
