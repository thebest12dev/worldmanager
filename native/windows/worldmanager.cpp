#include <cstdlib> 
#include <string>
#include <iostream>


int main(int argc, const char* argv[])
{
    std::string args = "\"bin\\java.exe\" -cp worldmanager/worldmanager.jar;worldmanager/lib/* com.thebest12lines.worldmanager.launcher.Launcher";
    for (int i = 0; i < argc-1; i++)
    {
        args = args+" "+argv[i];
    }
    

    system(args.c_str());
    return 0;
}
