package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;

import server.clip.region.ObjectDef;
import server.clip.region.Region;
import server.model.minigames.BarbarianDefence;
import server.model.minigames.DominionTower;
import server.model.minigames.Elvarg;
import server.model.minigames.FightCaves;
import server.model.minigames.FightPits;
import server.model.minigames.Goblin;
import server.model.minigames.HardCaves;
import server.model.minigames.Lottery;
import server.model.minigames.Nomad;
import server.model.minigames.PestControl;
import server.model.minigames.RFD;
import server.model.npcs.NPCDrops;
import server.model.npcs.NPCHandler;
import server.model.players.Highscores;
import server.model.players.PlayerHandler;
import server.model.players.content.Auction;
import server.net.ConnectionHandler;
import server.net.ConnectionThrottleFilter;
import server.task.Task;
import server.task.TaskScheduler;
import server.util.ControlPanel;
import server.util.SimpleTimer;
import server.util.log.Logger;
import server.world.ClanChatHandler;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.ShopHandler;

public class Server {

	public static boolean sleeping;
	public static int cycleRate;
	public static boolean UpdateServer = false;
	public static long lastMassSave = System.currentTimeMillis();
	public static SimpleTimer engineTimer, debugTimer;
	public static long cycleTime, cycles, totalCycleTime, sleepTime;
	public static boolean shutdownServer = false;		
	public static boolean shutdownClientHandler;			
	public static int serverlistenerPort; 
	public static ItemHandler itemHandler = new ItemHandler();
	public static PlayerHandler playerHandler = new PlayerHandler();
	public static BarbarianDefence barbDefence = new BarbarianDefence();
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
	public static FightPits fightPits = new FightPits();
	public static PestControl pestControl = new PestControl();
	public static ControlPanel panel = new ControlPanel(true); // false if you want it off
	public static int days, hours, minutes, secundes;
	public static NPCDrops npcDrops = new NPCDrops();
	public static ClanChatHandler clanChat = new ClanChatHandler();
	public static FightCaves fightCaves = new FightCaves();
    public static DominionTower dominionTower = new DominionTower();
    public static HardCaves hardCaves = new HardCaves();
    public static Lottery lottery = new Lottery();
    public static Nomad Nomad = new Nomad();
	public static Elvarg Elvarg = new Elvarg();
	public static Goblin Goblin = new Goblin();
	public static RFD rfd = new RFD();
	public static long[] TIMES = new long[5];

	private static IoAcceptor acceptor;
	private static ConnectionHandler connectionHandler;
	private static ConnectionThrottleFilter throttleFilter;
	private static DecimalFormat debugPercentFormat;
	public static boolean isDoubleExpWeekend = false;
	
	public static String currentBidder;
	public static String auctioneer;
	public static int currentBid;
	public static int itemID;
	public static long auctionTime;
	public static long startTime;
	public static long timePassed;
	public static int itemAmount;
	public static long lastTime = 0;
	public static boolean activeAuction = false;
	public static Auction auction = new Auction();
	public static boolean canAuctionBeCompleted = false;
	
	public static Auction getAuction() {
		return auction;
	}

	public static void shutdown() {
    shutdownServer = true;
    System.exit(0);
    }
     
    /**
	* The task scheduler.
	*/
	private static final TaskScheduler scheduler = new TaskScheduler();

	/**
	 * Gets the task scheduler.
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}
	
	static {
		if(!Config.SERVER_DEBUG) {
			serverlistenerPort = 43594;
		} else {
			serverlistenerPort = 43594;
		}
		cycleRate = 474;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}
	

	public static void main(java.lang.String args[]) throws NullPointerException, IOException {

		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));

		System.out.println("Power-Ps Server Started.");

	//	Highscores.process();

		if (Highscores.connected) {
		System.out.println("Connected to MySQL Database!");
		} else {
		System.out.println("Failed to connect to MySQL Database!");
		}

		acceptor = new SocketAcceptor();
		connectionHandler = new ConnectionHandler();
		
		SocketAcceptorConfig sac = new SocketAcceptorConfig();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		
		throttleFilter = new ConnectionThrottleFilter(Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", throttleFilter);
		acceptor.bind(new InetSocketAddress(serverlistenerPort), connectionHandler, sac);


		Connection.initialize();
		ObjectDef.loadConfig();
		Region.load();
        System.gc();
        lottery.process();
                
                final GameEngine engine = new GameEngine();
                scheduler.schedule(new Task() {
                    @Override
                    
                    public void execute() {
                            engine.run();
                        }
                    });

		/**
		 * Main Server Tick
		 */
        while (Server.shutdownServer) {
			acceptor = null;
			connectionHandler = null;
			sac = null;
			System.exit(0);
	        break;
        }
	}
	

	
	
	public static boolean playerExecuted = false;
	public static void debug() {
		if (debugTimer.elapsed() > 360*1000 || playerExecuted) {
			long averageCycleTime = totalCycleTime / cycles;
			double engineLoad = ((double) averageCycleTime / (double) cycleRate);
			System.out.println("Players online: " + PlayerHandler.getPlayerCount()+ ", engine load: "+ debugPercentFormat.format(engineLoad));
			totalCycleTime = 0;
			cycles = 0;
			System.gc();
			System.runFinalization();
			debugTimer.reset();
			playerExecuted = false;
		}

	}
	public static void debug2() {
		long averageCycleTime = totalCycleTime / cycles;
		System.out.println("Average Cycle Time: " + averageCycleTime + "ms");
		double engineLoad = ((double) averageCycleTime / (double) cycleRate);
		System.out.println("Players online: " + PlayerHandler.getPlayerCount()+ ", engine load: "+ debugPercentFormat.format(engineLoad));
		totalCycleTime = 0;
		cycles = 0;
		System.gc();
		System.runFinalization();
		debugTimer.reset();
		playerExecuted = false;
	}
	
	public static long getSleepTimer() {
		return sleepTime;
	}
	
}
