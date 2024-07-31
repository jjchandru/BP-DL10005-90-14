#!/bin/bash

# Wait until the VS Code server is ready
while ! command -v code &> /dev/null
do
	echo "Waiting for VS Code server to be ready..."
	sleep 2
done

# Install the extension
code --install-extension /workspaces/BP-DL10005-90-13/.devcontainer/ai-usage-analytics-0.0.1.vsix