rm -rf ../temp
mkdir ../temp
cp ./* -rf ../temp
cd ../temp/build
ant
cp ROOT.war ../../
cd ../..
rm -rf temp
