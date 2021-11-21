# Imazipper
Combine two file into one file &amp; Split combined file into two file.

***Java 11 and avobe required.***


## How-to-use

**Usage (GUI)** :

Download pre-built binaries at release, unzip it.

_(Linux/MacOS)_ type ``bash ./bin/imazipper-gui`` in terminal.

_(Windows)_ run ``./bin/imazipper-gui.bat`` in file explorer or type ``./bin/imazipper-gui.bat`` in cmd.


**Usage (CLI)** : 

_(Linux/MacOS)_ type ``bash ./bin/imazipper [arguments]`` in terminal.

_(Windows)_ type ``./bin/imazipper.bat [arguments]`` in cmd.

_(Universal)_ type ``java -jar ./lib/imazipper_cui.jar`` in terminal/cmd.

<br />

_CLI Arguments:_

```
Usage: Imazipper CLI [-hv] [-a=FILE] [-f=FILE] [-i=FILE] [-o=FILE/FOLDER]
                     [-t=<JobType>]
De/Combines Image and archive.
  -a, --archive=FILE         target archive file to combine (Combine task only
                               option)
  -f, --file=FILE            target file to split (Split task only option)
  -h, --help                 Display this help message
  -i, --image=FILE           target image file to combine (Combine task only
                               option)
  -o, --output=FILE/FOLDER   target File/Folder to save your task output
                             If file/folder doesn't specified, output
                               file/folder will automatically specified.
  -t, --type=<JobType>       selects type of task, only allows ["combine"/"
                               split"] option.
  -v, --verbose              print more detail logs.
```
