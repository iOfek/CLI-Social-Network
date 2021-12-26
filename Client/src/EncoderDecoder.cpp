#include <string>
#include <sstream> 
#include <map>
#include <encoderDecoder.h>
#include <vector>
#include <iostream>
EncoderDecoder::EncoderDecoder(){}

using std::string;
std::vector<char> EncoderDecoder::encode(std::string line){
    std::stringstream ss(line);
    std::string word;
    ss>> word;
    Opcode opcode =  stringActionToOpcode.at(word);
    std::vector<char> encodedMessage;
    OcodeToBytes(opcode,encodedMessage);
    switch (opcode)
    {
        case REGISTER:
            registerEncoding(line,encodedMessage);
            break;
        case LOGIN:
            loginEncoding(line,encodedMessage);
            break;
        case LOGOUT:
            break;
        default:
            break;
    }
    encodedMessage.insert(encodedMessage.end(),';');
    return encodedMessage;
}

void EncoderDecoder::OcodeToBytes( Opcode opcode,std::vector<char> &encodedMessage){
    char bytesArr[2];
    shortToBytes(static_cast<short>(opcode),bytesArr);
    encodedMessage.push_back(bytesArr[0]);
    encodedMessage.push_back(bytesArr[1]);   
}

void EncoderDecoder::registerEncoding(std::string line, std::vector<char> &encodedMessage){
    line = line.substr(9);
    for (size_t i = 0; i < line.size(); i++){   
        if(line[i]== ' ')
            encodedMessage.insert(encodedMessage.end(),'\0');
        else
            encodedMessage.insert(encodedMessage.end(),line[i]);
    }
    
}

void EncoderDecoder::loginEncoding(std::string line, std::vector<char> &encodedMessage){
    line = line.substr(6);
    for (size_t i = 0; i < line.size(); i++){   
        if(line[i]== ' ')
            encodedMessage.insert(encodedMessage.end(),'\0');
        else
            encodedMessage.insert(encodedMessage.end(),line[i]);
    }
    
}
void EncoderDecoder::followEncoding(std::string line, std::vector<char> &encodedMessage){
    line = line.substr(7);
    for (size_t i = 0; i < line.size(); i++){   
        if(line[i]== ' ')
            encodedMessage.insert(encodedMessage.end(),'\0');
        else
            encodedMessage.insert(encodedMessage.end(),line[i]);
    }
    
}


std::string EncoderDecoder::decode(std::string encodedMessage){
    char bytesArr[] = {encodedMessage[0],encodedMessage[1]};
    switch (static_cast<int>(bytesToShort(bytesArr))){
    case 9 /* constant-expression */:
        return decodeNotification(encodedMessage);
        break;
    case 10:
        return decodeAck(encodedMessage);
        break;
    case 11:
        return decodeError(encodedMessage);
        break;    
    default:
        return NULL;
        break;
    }
}
std::string EncoderDecoder::decodeNotification(std::string encodedMessage){
    std::string decodedMessage = "NOTIFICATION ";
    int type = (int)encodedMessage[2];
    if(type == 0)//PM
        decodedMessage += "PM ";
    else//Public 
        decodedMessage += "Public ";
    for(size_t i= 3; i < encodedMessage.size();i++){
        if(encodedMessage[i]=='\0')
            encodedMessage += " ";
        else    
            encodedMessage += encodedMessage[i];
    }
    return decodedMessage;
}
std::string EncoderDecoder::decodeAck(std::string encodedMessage){
    std::string decodedMessage = "ACK ";
    char bytesArr[] = {encodedMessage[2],encodedMessage[3]};
    int a =static_cast<int>(bytesToShort(bytesArr));
    std::cout << a << "\n";
    decodedMessage +=  std::to_string(a);
    return decodedMessage;
}
std::string EncoderDecoder::decodeError(std::string encodedMessage){
    std::string decodedMessage = "ERROR";
    return decodedMessage;
}
