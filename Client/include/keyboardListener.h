#include <iostream>
#include <mutex>
#include <thread>
#include <encoderDecoder.h>
#include <connectionHandler.h>


class KeyboardListener{
    private:
        EncoderDecoder * encoderDecoder;
        ConnectionHandler * connectionHandler;
        //std::mutex &mutex;
    public:
        KeyboardListener(EncoderDecoder *_encoderDecoder,ConnectionHandler * _connectionHandler);
       
        void run();
};