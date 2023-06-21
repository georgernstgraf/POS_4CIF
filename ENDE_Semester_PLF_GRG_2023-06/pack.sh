#!/bin/sh
zipfile=../`basename "$(pwd)"`.zip
set -v
rm -f $zipfile
zip -r $zipfile . -i@include.lst
