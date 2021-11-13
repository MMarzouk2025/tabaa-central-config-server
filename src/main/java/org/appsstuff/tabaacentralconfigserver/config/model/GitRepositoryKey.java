package org.appsstuff.tabaacentralconfigserver.config.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.server.environment.JGitEnvironmentProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
@ConfigurationProperties("git.repo.key")
public class GitRepositoryKey {
    
    private JGitEnvironmentProperties jGitEnvironmentProperties;
    private String path;
    
    public GitRepositoryKey(JGitEnvironmentProperties jGitEnvironmentProperties) {
        this.jGitEnvironmentProperties = jGitEnvironmentProperties;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        setJGitEnvironmentPropertiesRelatedToPrivateKeyByPath(path);
        this.path = path;
    }
    
    private void setJGitEnvironmentPropertiesRelatedToPrivateKeyByPath(String Path) {
        jGitEnvironmentProperties.setIgnoreLocalSshSettings(true);
        jGitEnvironmentProperties.setPrivateKey(getPrivateKeyContent(Path));
        jGitEnvironmentProperties.setStrictHostKeyChecking(false);
    }
    
    private String getPrivateKeyContent(String keyPath) {
        StringBuilder fileContent = new StringBuilder();
        try {
            File keyFile = new ClassPathResource(keyPath).getFile();
            BufferedReader reader = new BufferedReader(new FileReader(keyFile));
            String line;
            fileContent.append("|");
            while ((line = reader.readLine()) != null) {
                fileContent.append("\n").append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
    
}
