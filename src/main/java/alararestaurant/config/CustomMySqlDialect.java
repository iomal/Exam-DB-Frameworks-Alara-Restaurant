package alararestaurant.config;

import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMySqlDialect extends MySQL57Dialect {
    public CustomMySqlDialect() {
        super();

        registerFunction(
                "group_concat",
                new StandardSQLFunction(
                        "group_concat",
                        StandardBasicTypes.STRING
                )
        );
    }
}