#include <iostream>
#include <mutex>
#include <thread>
#include <encoderDecoder.h>


class KeyboardListener{
    private:
        std::mutex & _mutex;
        EncoderDecoder & encoderDecoder;
    public:
    
        KeyboardListener(std::mutex& mutex,EncoderDecoder &_encoderDecoder); 
       
        void run();
};