package ServerInterface;

import java.net.Socket;
import java.util.Scanner;

/**
 * 监听服务器消息的线程
 */
public class ListenThread extends Thread{
	Socket socket;
	OriginInterface originInterface;

	public ListenThread(Socket socket, OriginInterface originInterface){
		this.socket = socket;
		this.originInterface = originInterface;
	}

	@Override
	public void run(){
		try {
			Scanner scanner = new Scanner(socket.getInputStream());
			while(scanner.hasNext()){
				String commandsLine = scanner.nextLine();
				String [] commands = commandsLine.split("#");
				switch (commands[0]){
					case "ON_RESPOND_REGISTER":
						originInterface.onRespondRegister(Boolean.parseBoolean(commands[1]), commands[2]);
						break;
					case "ON_RESPOND_LOGIN":
						originInterface.onRespondLogin(Boolean.parseBoolean(commands[1]), Integer.parseInt(commands[2]), commands[3]);
						break;
					case "ON_RESPOND_GET_TABLES":
						originInterface.onRespondGetTables(TableInfo.stringToTableInfoArray(commands[1]));
						break;
					case "ON_RESPOND_ENTER_TABLE":
						originInterface.onRespondEnterTable(Integer.parseInt(commands[1]),Boolean.parseBoolean(commands[2]), commands[3]);
						break;
					case "ON_TABLE_CHANGE":
						// 服务器返回 ON_TABLE_CHANGE#对手用户名#对手分数#自己是否举手#对手是否举手#游戏是否进行中#棋盘的逻辑数组#自己是否执黑子#是否轮到自己下
						int[][] board = new int[15][15];
						int index = 0;
						for(int i = 0; i < 15; ++i)
							for(int j = 0; j < 15; ++j){
								board[i][j] = commands[6].charAt(index++) - '0';
							}
						originInterface.onTableChange(
								new PlayerInfo(commands[1], Integer.parseInt(commands[2])),
								Boolean.parseBoolean(commands[3]),
								Boolean.parseBoolean(commands[4]),
								Boolean.parseBoolean(commands[5]),
								board,
								Boolean.parseBoolean(commands[7]),
								Boolean.parseBoolean(commands[8])
						);
						break;
					case "ON_GAME_OVER":
						originInterface.onGameOver(Boolean.parseBoolean(commands[1]),Boolean.parseBoolean(commands[2]),Boolean.parseBoolean(commands[3]));
						break;
					case "ON_RESPOND_RETRACT":
						originInterface.onRespondRetract(Boolean.parseBoolean(commands[1]));
						break;
					case "ON_OPPONENT_RETRACT":
						originInterface.onOpponentRetract();
						break;
					case "ON_RECEIVE_MESSAGE":
						originInterface.onReceiveMessage(commands[1]);
						break;
					case "ON_RESPOND_QUIT_TABLE":
						originInterface.onRespondQuitTable(Boolean.parseBoolean(commands[1]));
						break;
					default:
						throw new Exception("Wrong command:" + commandsLine);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			originInterface.onLostConnection(e.getMessage());
		}
	}
}
