#include <iostream>
#include <mutex>
#include <thread>
#include <connectionHandler.h>


class KeyboardListener{
    private:
        std::mutex & _mutex;
        ConnectionHandler & _connectionHandler;
    public:
    
        KeyboardListener(std::mutex& mutex,ConnectionHandler &connectionHandler); 
       
        void run();
};