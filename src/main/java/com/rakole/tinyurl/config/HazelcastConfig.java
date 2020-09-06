package com.rakole.tinyurl.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HazelcastConfig {

    @Value("${custom.cache.ttl:20}")
    private int cacheTimeToLive;

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName("hazelcast-cache");
        MapConfig shortUrlCache = new MapConfig();
        shortUrlCache.setTimeToLiveSeconds(cacheTimeToLive);
        shortUrlCache.setEvictionPolicy(EvictionPolicy.LFU);
        config.getMapConfigs().put("short_url_cache", shortUrlCache);
        return config;
    }
}
