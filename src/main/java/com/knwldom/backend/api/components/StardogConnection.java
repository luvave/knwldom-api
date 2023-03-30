package com.knwldom.backend.api.components;

import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.ext.spring.DataSource;
import com.complexible.stardog.ext.spring.SnarlTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class StardogConnection {
    private SnarlTemplate snarlTemplate;

    public StardogConnection(
        @Value("${stardog.remoteServer}")
        final String remoteServer,
        @Value("${stardog.database}")
        final String database,
        @Value("${stardog.user}")
        final String user,
        @Value("${stardog.password}")
        final String password
    ) {
        ConnectionConfiguration cc = ConnectionConfiguration
                .to(database)
                .server(remoteServer)
                .credentials(user, password)
                .reasoning(true);

        SnarlTemplate st = new SnarlTemplate();
        st.setDataSource(new DataSource(cc));

        snarlTemplate = st;
    }

    public SnarlTemplate getSnarlTemplate() {
        return snarlTemplate;
    }
}
