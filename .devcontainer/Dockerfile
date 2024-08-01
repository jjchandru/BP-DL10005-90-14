# Use an appropriate base image
FROM mcr.microsoft.com/vscode/devcontainers/base:ubuntu-20.04

# Install JDK 17
RUN apt-get update && \
	apt-get install -y openjdk-17-jdk

# Install Python 3
RUN apt-get install -y python3 python3-pip

# Install Node.js 20.16
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
	apt-get install -y nodejs

# Clean up
RUN apt-get clean && \
	rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME environment variable
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH