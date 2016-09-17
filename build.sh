rm -rf ../temp
mkdir ../temp
cp -r ./* ../temp
cd ../temp/build
ant
cp ROOT.war ../../
cd ../..
rm -rf temp
