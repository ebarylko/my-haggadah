# haggadah

## Development
* `npm install`
*Launch spacemacs and start cljs repl: `, -> ' -> shadow -> :app`
*This should start the browser on `localhost:8082`
* Start watch for tailwind css: `npm run tailwind:watch `

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

## Runnning firebase emulators
run `firebase emulators:start`

## Deploying firebase
run `firebase deploy`. If you only want to test specific aspect of deployment, such as hosting or functions, run `firebase deploy --only "specific aspect"`.

## Naming branches
Name the branch as the feature you are working on in the branch

## Veryiying tests with firebase emulator
Run `firebase emulators:exec 'npm test`

## Verifying integration tests with firebase emulator
Run `firebase emulators:exec 'npm run integration-test'`
