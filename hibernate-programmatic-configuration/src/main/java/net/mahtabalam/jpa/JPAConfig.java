package net.mahtabalam.jpa;

import jakarta.persistence.EntityManagerFactory;
import net.mahtabalam.domain.Department;
import net.mahtabalam.domain.Employee;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.Metadata;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class JPAConfig {
    private static final Map<String, Object> settings = new HashMap<>();

    static {
        // Database connection settings
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/test");
        settings.put("hibernate.connection.username", "postgres");
        settings.put("hibernate.connection.password", "postgres");
        
        // Hibernate settings
        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        settings.put("hibernate.highlight_sql", "true");
        settings.put("hibernate.current_session_context_class", "thread");
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        // Create the ServiceRegistry from hibernate settings
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);

        // Add annotated classes
        sources.addAnnotatedClass(Department.class);
        sources.addAnnotatedClass(Employee.class);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        return metadata.getSessionFactoryBuilder()
                .build()
                .unwrap(EntityManagerFactory.class);
    }
}