#include <iostream>
#include <mutex>
#include <thread>
#include <serverListener.h>


        
    
ServerListener::ServerListener(EncoderDecoder &_encoderDecoder,ConnectionHandler & _connectionHandler): encoderDecoder(_encoderDecoder),connectionHandler(_connectionHandler){};
       
void ServerListener::run(){
        // std::lock_guard<std::mutex> lock(_mutex); // constructor locks the mutex while destructor (out of scope) unlocks it
        while (1){
            // We can use one of three options to read data from the server:
            // 1. Read a fixed number of characters
            // 2. Read a line (up to the newline character using the getline() buffered reader
            // 3. Read up to the null character
            std::string encodedMessage;
            // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
            // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
            if (!connectionHandler.getLine(encodedMessage)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
            std::string answer =encoderDecoder.decode(encodedMessage);
            
            std::cout << "Reply: " << answer  << std::endl << std::endl;
            std::stringstream ss(answer);
            std::string word;
            ss >> word;
            if (word.compare("ACK") == 0) {
                ss>> word;
                if(word.compare("3") == 0){
                    std::cout << "Exiting...\n" << std::endl;
                    break; 
                }
                    
            }
        }
        
} 