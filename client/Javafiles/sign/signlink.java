package sign;

import java.applet.Applet;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public final class signlink
  implements Runnable
{
  public static final int clientversion = 317;
  public static int uid;
  public static int storeid = 32;
  public static RandomAccessFile cache_dat = null;
  public static final RandomAccessFile[] cache_idx = new RandomAccessFile[5];
  public static boolean sunjava;
  public static Applet mainapp = null;
  private static boolean active;
  private static int threadliveid;
  private static InetAddress socketip;
  private static int socketreq;
  private static Socket socket = null;
  private static int threadreqpri = 1;
  private static Runnable threadreq = null;
  private static String dnsreq = null;
  public static String dns = null;
  private static String urlreq = null;
  private static DataInputStream urlstream = null;
  private static int savelen;
  private static String savereq = null;
  private static byte[] savebuf = null;
  private static boolean midiplay;
  private static int midipos;
  public static String midi = null;
  public static int midivol;
  public static int midifade;
  private static boolean waveplay;
  private static int wavepos;
  public static int wavevol;
  public static boolean reporterror = true;
  public static String errorname = "";

  public static void startpriv(InetAddress inetaddress)
  {
    threadliveid = (int)(Math.random() * 99999999.0D);
    if (active)
    {
      try
      {
        Thread.sleep(500L);
      } catch (Exception localException) {
      }
      active = false;
    }
    socketreq = 0;
    threadreq = null;
    dnsreq = null;
    savereq = null;
    urlreq = null;
    socketip = inetaddress;
    Thread thread = new Thread(new signlink());
    thread.setDaemon(true);
    thread.start();
    while (!active)
      try
      {
        Thread.sleep(50L);
      } catch (Exception localException1) {
      }
  }

  public static void setuid() {
    uid = 75894;
  }

  public void run() {
    active = true;
    String s = findcachedir();

    uid = 75894;
    try {
      cache_dat = new RandomAccessFile(s + "main_file_cache.dat", "rw");
      for (int j = 0; j < 5; j++)
        cache_idx[j] = new RandomAccessFile(s + "main_file_cache.idx" + j, "rw");
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
    for (int i = threadliveid; threadliveid == i; )
    {
      if (socketreq != 0)
      {
        try
        {
          socket = new Socket(socketip, socketreq);
        }
        catch (Exception _ex)
        {
          socket = null;
        }
        socketreq = 0;
      }
      else if (threadreq != null)
      {
        Thread thread = new Thread(threadreq);
        thread.setDaemon(true);
        thread.start();
        thread.setPriority(threadreqpri);
        threadreq = null;
      }
      else if (dnsreq != null)
      {
        try
        {
          dns = InetAddress.getByName(dnsreq).getHostName();
        }
        catch (Exception _ex)
        {
          dns = "unknown";
        }
        dnsreq = null;
      }
      else if (savereq != null)
      {
        if (savebuf != null)
          try
          {
            FileOutputStream fileoutputstream = new FileOutputStream(s + savereq);
            fileoutputstream.write(savebuf, 0, savelen);
            fileoutputstream.close();
          } catch (Exception localException1) {
          }
        if (waveplay)
        {
          String wave = s + savereq;
          waveplay = false;
        }
        if (midiplay)
        {
          midi = s + savereq;
          midiplay = false;
        }
        savereq = null;
      }
      else if (urlreq != null)
      {
        try
        {
          System.out.println("urlstream");
          urlstream = new DataInputStream(new URL(mainapp.getCodeBase(), urlreq).openStream());
        }
        catch (Exception _ex)
        {
          urlstream = null;
        }
        urlreq = null;
      }
      try
      {
        Thread.sleep(50L);
      }
      catch (Exception localException2)
      {
      }
    }
  }

  public static final String findcachedir()
  {
    File[] drives = File.listRoots();

    String HomeIsBaws = System.getProperty("user.home");
    String s = HomeIsBaws + "/Power-PsV2.2/";
    File file = new File(s);
    if ((file.exists()) || (file.mkdirs())) {
      return s + "/";
    }
    return null;
  }

  public static int getuid(String s) {
    return 75894;
  }

  public static synchronized Socket opensocket(int i)
    throws IOException
  {
    for (socketreq = i; socketreq != 0; )
      try
      {
        Thread.sleep(50L);
      }
      catch (Exception localException) {
      }
    if (socket == null) {
      throw new IOException("could not open socket");
    }
    return socket;
  }

  public static synchronized DataInputStream openurl(String s)
    throws IOException
  {
    for (urlreq = s; urlreq != null; )
      try
      {
        Thread.sleep(50L);
      }
      catch (Exception localException) {
      }
    if (urlstream == null) {
      throw new IOException("could not open: " + s);
    }
    return urlstream;
  }

  public static synchronized void dnslookup(String s)
  {
    dns = s;
    dnsreq = s;
  }

  public static synchronized void startthread(Runnable runnable, int i)
  {
    threadreqpri = i;
    threadreq = runnable;
  }

  public static synchronized boolean wavesave(byte[] abyte0, int i)
  {
    return false;
  }

  public static synchronized boolean wavereplay()
  {
    if (savereq != null)
    {
      return false;
    }

    savebuf = null;
    waveplay = true;
    savereq = "sound" + wavepos + ".wav";
    return true;
  }

  public static synchronized void midisave(byte[] abyte0, int i)
  {
  }

  public static void reporterror(String s)
  {
    System.out.println("Error: " + s);
  }
}