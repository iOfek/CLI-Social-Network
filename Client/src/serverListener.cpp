#include <iostream>
#include <mutex>
#include <thread>
#include <serverListener.h>


        
    
ServerListener::ServerListener(std::mutex& mutex,ConnectionHandler &connectionHandler): _mutex(mutex), _connectionHandler(connectionHandler){}; 
       
void ServerListener::run(){
    std::lock_guard<std::mutex> lock(_mutex); // constructor locks the mutex while destructor (out of scope) unlocks it
} 