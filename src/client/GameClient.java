package client;

public class GameClient {

	private MainCanvas canvas;

	public static void main(String[] args) {
		
		
		
		GameClient toto = new GameClient();
		toto.InitMain();
	}
	
	public void InitMain(){
		this.canvas = new MainCanvas();
		this.canvas.Init();
	}
	
	

}
