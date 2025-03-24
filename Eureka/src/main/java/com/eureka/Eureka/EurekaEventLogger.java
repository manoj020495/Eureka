package com.eureka.Eureka;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationEvent;

@Component
public class EurekaEventLogger implements SmartApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(EurekaEventLogger.class);
    private final EurekaClient eurekaClient;

    public EurekaEventLogger(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return EurekaInstanceRegisteredEvent.class.isAssignableFrom(eventType)
                || EurekaInstanceCanceledEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof EurekaInstanceRegisteredEvent registeredEvent) {
            InstanceInfo instanceInfo = registeredEvent.getInstanceInfo();
            logger.info("✅ Service Registered: {} (Instance ID: {}, IP: {}, Port: {})",
                    instanceInfo.getAppName(),
                    instanceInfo.getInstanceId(),
                    instanceInfo.getIPAddr(),
                    instanceInfo.getPort());
        } else if (event instanceof EurekaInstanceCanceledEvent canceledEvent) {
            logger.info("❌ Service Deregistered: {} (Instance ID: {})",
                    canceledEvent.getAppName(),
                    canceledEvent.getServerId());
        }
    }

    // 🔄 Log all currently registered services every minute
    @Scheduled(fixedRate = 60000)
    public void logAllRegisteredServices() {
        Applications applications = eurekaClient.getApplications();
        logger.info("📋 Eureka Registry Snapshot:");
        applications.getRegisteredApplications().forEach(app -> {
            logger.info("🔸 {}", app.getName());
            app.getInstances().forEach(instance -> logger.info(
                    "   ↳ Instance ID: {}, IP: {}, Port: {}",
                    instance.getInstanceId(),
                    instance.getIPAddr(),
                    instance.getPort()));
        });
    }
}
