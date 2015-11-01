Alunos:
    Rui Miguel Capela Fonseca   2013145105
    Pedro Cardoso Belém         2013143231
    Mário Rui de Almeida Balça  2012139135

Como executar o projecto:
    RMIServer:
        Abrir a pasta RMIServer na consola e correr o comando java -jar RMIServer.jar, 
        O ficheiro configRMI.txt contém as seguintes configs:
            Endereço IP
            Porta
            Intervalo de Tempo para verificar se algum projecto ultrapassou a deadline
        
    TCP Servers:
        Abrir a pasta Server na consola e correr o comando java -jar Server.jar
        O ficheiro configServer.txt contém as seguintes configs:
            Porta TCP
            Porta UDP
            Endereço IP Servidor Secundário
            Porta UDP Servidor Secundário
            Endereço IP Servidor RMI
            Porta Servidor RMI
            Timeout Pings UDP

    Clientes:
        Abrir a pasta Clientes na consola e correr o comando java -jar Client.jar
        O ficheiro configClient.txt contém as seguintes configs:
            Endereço IP do Servidor Primário
            Endereço IP do Servidor Secundário
            Porta do Servidor Primário
            Porta do Servidor Secundário
            Timeout para o Socket TCP
            
