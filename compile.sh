#!/bin/bash
# Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
# reserved. Use of this source code is governed by a BSD-style license
# that can be found in the LICENSE file.

# Compile the test program.
javac -cp "./bin:./bin/*" ./bin/tests/simple/*.java ./bin/tests/simple/*.java

# Create the test JAR file.
cd bin
jar -cf jcef-tests.jar tests/simple/*.class tests/simple/*.java
cd ..
