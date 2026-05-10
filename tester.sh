echo Running tests...
cd ~/GitHub/Prog2-LIA/
mvn test -e -X -B -f prog2-lia/pom.xml > ../log.txt
echo '
Account tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.AccountTest.txt' >> ../log.txt 2>/dev/null
echo '
App tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.AppTest.txt' >> ../log.txt 2>/dev/null
echo '
Client tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.ClientTest.txt' >> ../log.txt 2>/dev/null
echo '
Driver tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.DriverTest.txt' >> ../log.txt 2>/dev/null
echo '
IdCreator tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.IdCreatorTest.txt' >> ../log.txt 2>/dev/null
echo '
Transaction tests:
' >> ../log.txt
cat 'target/surefire-reports/com.example.TransactionTest.txt' >> ../log.txt 2>/dev/null
echo Tests complete! Please see log.txt for details