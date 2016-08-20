import Adafruit_BBIO.GPIO as GPIO
import time
import urllib2
#print response.info()
GPIO.setup("P8_7",GPIO.IN) #egg1
GPIO.setup("P8_8",GPIO.IN) #egg2
GPIO.setup("P8_11",GPIO.IN) #egg3
GPIO.setup("P8_12",GPIO.IN) #egg4
while True:
        counter=0
	if GPIO.input("P8_8")==1:
                print 'egg1 absent'
		#response = urllib2.urlopen('http://cedtnsit.in/rohan/set.php?name=LED1&val=Egg1')
		time.sleep(2)	else:
                print 'egg1 present'
		#response = urllib2.urlopen('http://cedtnsit.in/rohan/set.php?name=LED1&val=NoEggresponse = urllib2.
		counter=counter+1
		time.sleep(2)     
  	if GPIO.input("P8_7")==1:
                print 'egg2 absent'
                time.sleep(2)        else:
                print 'egg2 present'
		counter=counter+1
                time.sleep(2)	if GPIO.input("P8_11")==1:
                print 'egg3 present'
		counter=counter+1
                time.sleep(2)
        else:
                print 'egg3 absent'
                time.sleep(2)
	if GPIO.input("P8_12")==1:
                print 'egg4 absent'
                time.sleep(2)
        else:
                print 'egg4 present'
		counter=counter+1
                time.sleep(2)
	print counter
	response = urllib2.urlopen('http://cedtnsit.in/rohan/set.php?name=LED1&val=%s'%counter)
	print counter
	counter=0
