#!/usr/bin/env bash

echo && echo "********************************* Installing Homebrew *********************************" && echo
which brew
brew --version
if [ $? = 1 ]; then
  /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
fi

echo && echo "********************************** Installing Make ************************************" && echo
which make
make -version
if [ $? = 1 ]; then
  brew install make
fi

echo && echo "********************************** Installing Gradle **********************************" && echo
./gradlew


echo && echo "****************************** Installing SdkMan ******************************" &&
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version


echo && echo "******************* Installing Graal native image toolchain (needed for Mac) *******************" &&
echo && echo "***************** Installing Graal native image pre-requisites ****************" &&
echo
# More details can be found here https://www.graalvm.org/22.2/reference-manual/native-image/#prerequisites
xcode-select --install
if [ $? = 1 ]; then
  echo ">>> Information :: It seems xcode is already installed on this system."
fi

echo && echo "******************** Installing Graal native Image Compiler and OpenJDK 17 *******************" &&
sdk install java 17.0.4-tem
sdk use java 17.0.4-tem
sdk install java 22.2.r17-grl
sdk use java 22.2.r17-grl