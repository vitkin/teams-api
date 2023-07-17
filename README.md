# Teams API

## Requirements

### Node.js

See [Installing Node.js via package manager](https://nodejs.org/en/download/package-manager).

Prefer using the latest LTS version which is version 18 by this time of writing.

### Yarn

Install Yarn using Corepack by following the instructions
[here](https://yarnpkg.com/getting-started/install).

### Project teams-token

```sh
mkdir -p ~/workspaces/github.com/fossteams
cd ~/workspaces/github.com/fossteams
git clone https://github.com/fossteams/teams-token.git
cd teams-token
yarn install
```

### Java

Install the latest version of Java, preferably the LTS one that is 17 by this
time of writing.

For instance in Debian or Ubuntu distributions, you can simply install the
package `openjdk-17-jdk` or just `default-jdk` that always depends on the
latest LTS version.

## Usage

1. Go to `~/workspaces/github.com/fossteams/teams-token`.
2. Run `yarn start`
3. Authenticate using the email address of the account you want to export the
  conversations.
4. You can check the 3 JWT token files were created under `~/.config/fossteams`.
5. Run the tool using the `bin/run.sh` script.

> **Note:** You don't need to systematically renew the JWT tokens (`yarn start`)
> but bear in mind they have an expiry date.
