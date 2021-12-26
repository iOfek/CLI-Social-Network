#include <iostream>
#include <mutex>
#include <thread>
#include <connectionHandler.h>


class ServerListener{
    private:
        std::mutex & _mutex;
        ConnectionHandler & _connectionHandler;
    public:
    
        ServerListener(std::mutex& mutex,ConnectionHandler &connectionHandler); 
       
        void run();
};