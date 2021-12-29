#include <iostream>
#include <mutex>
#include <thread>
#include <keyboardListener.h>


        
    
KeyboardListener::KeyboardListener(std::mutex& mutex,EncoderDecoder &_encoderDecoder): _mutex(mutex), encoderDecoder(_encoderDecoder){}; 
       
void KeyboardListener::run(){

    // std::lock_guard<std::mutex> lock(_mutex); // constructor locks the mutex while destructor (out of scope) unlocks it
    while(1){
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
		std::string line(buf);
        std::vector<char> encodedLine = encoderDecoder.encode(line);
		int len=encodedLine.size();
       //std::cout << encodedLine<<"\n";
        
        char buff [encodedLine.size()];
        for (int i = 0; i < len; i++)
        {
            buff[i] = encodedLine[i];
        }
        
        
		// connectionHandler.sendLine(line) appends '\n' to the message. Therefor we send len+1 bytes.
        std::cout << "Sent " << len+1 << " bytes to server" << std::endl;
    }
    
} 
