#include <cstdlib>
#include <string>
#include <iostream>

int main(int argc, const char* argv[]) {
    std::string args = "\"bin\\java.exe\" -cp worldmanager/worldmanager.jar;worldmanager/objects/main/1/* worldmanager.Launcher";
    for (int i = 1; i < argc; i++) {
        args = args + " " + argv[i];
    }

    int result = system(args.c_str());

    if (result == 2) {
        std::cerr << "Updating worldmanager..." << std::endl;
        std::remove("worldmanager/worldmanager.jar");
        std::rename("worldmanager_0.jar","worldmanager/worldmanager.jar");
       

        system(args.c_str());
    }

    return result;
}
