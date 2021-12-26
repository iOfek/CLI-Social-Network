#include <iostream>
#include <mutex>
#include <thread>
#include <keyboardListener.h>


        
    
KeyboardListener::KeyboardListener(std::mutex& mutex,ConnectionHandler &connectionHandler): _mutex(mutex), _connectionHandler(connectionHandler){}; 
       
void KeyboardListener::run(){
    std::lock_guard<std::mutex> lock(_mutex); // constructor locks the mutex while destructor (out of scope) unlocks it
} 
