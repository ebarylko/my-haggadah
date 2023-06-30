# haggadah

## Development
* `npm install`
*Launch spacemacs and start cljs repl: `, -> ' -> shadow -> :app`
*This should start the browser on `localhost:8082`
* Start watch for bulma css: `npm run css-watch `

## PR format

 ### Summary
 A description of what was done in this branch
 
### Changes
List the file changed, and note the changes applied to the file. Do this for all files edited. An example:

karma.conf.js

*added configuration to run tests using karma
*Added targets so that tests are run in a headless browser

## Changing workflows
If a workflow is changed, run `act pulll_request` to see if the change to the workflow works correctly. 

The container will be run without access to the local cache, leading to longer completion times. 

For quicker completion times, commit changes and push to remote, which will run the github actions with the local cache.

## Runnning firebase emulators
run `firebase emulators:start`

## Deploying firebase
run `firebase deploy`. If you only want to test specific aspect of deployment, such as hosting or functions, run `firebase deploy --only "specific aspect"`.

## Running acceptance tests
run `npm run test:acceptance`

## Running firebase emulators with previous emulator state data
run `npm run emulators`

## Naming branches
Name the branch as the feature you are working on in the branch

## Veryiying tests with firebase emulator
Run `firebase emulators:exec 'npm test`

## Running act with artifacts stored
run `npm run act:pr`

## Merging branches
Complete the pr. Then change to the main branch and run `git fetch -p`. After that run `git rebase origin/name-of-branch`. Finally, delete the local branch by running `git b -D name-of-branch`.

## User authentication model

### Possible states
There are three possible states for a user, unloaded, unregistered, and registered. The user will start off by being unloaded, and when logging in/out or refreshing the page the user will either be unregistered or registered. If the user has no uid, they will be marked as unregistered. Otherwise, they are marked as registered.

### Accessing pages
If a user does not have certain prerequisites to access a page, such as being logged in, the user will be moved to the home page. 
