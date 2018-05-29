#!/bin/bash
set -e
gradle printVersion || gradle bintrayUpload