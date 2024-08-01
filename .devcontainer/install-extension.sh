#!/bin/bash

# Wait until the VS Code server is ready
while ! command -v code &> /dev/null
do
	echo "Waiting for VS Code server to be ready..."
	sleep 2
done

# Check if WORKSPACE_FOLDER is set
if [ -z "$WORKSPACE_FOLDER" ]; then
	echo "Error: WORKSPACE_FOLDER environment variable is not set."
	exit 1
fi

# Install the extension
code --install-extension "$WORKSPACE_FOLDER/.devcontainer/ai-usage-analytics-0.0.1.vsix"