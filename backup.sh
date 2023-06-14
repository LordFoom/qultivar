#!/bin/bash

set -uf -o pipefail

cd "$(dirname "$0")/.." || exit

echo "starting backup"
FOLDERNAME=qultivar
if [ ! -d $FOLDERNAME ]; then
    echo "'$FOLDERNAME' folder not found, exiting"
    exit 1
fi

# clean the projects
echo "running './gradlew clean'"
cd $FOLDERNAME
./gradlew clean > /dev/null 2>&1
if [ "$?" -ne "0" ]; then
    echo "'./gradlew clean' process failed, exiting"
    exit 1
fi

# move back one folder
cd ..

# create the backup
echo "creating the backup"
FILENAME=qultivar-backup-$(date +%Y%m%d_%H%M%S).tar.gz
tar -czf $FILENAME qultivar

# copying the backup to secondary location
echo "creating a copy of the backup"
cp $FILENAME /mnt/c/Users/heeall02/MyStuff/

echo "backup complete (backup file name: $FILENAME)"
