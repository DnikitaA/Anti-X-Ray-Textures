package net.minecraft.src;

import net.minecraft.client.Minecraft;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class ProtectionTextures 
{
	public ProtectionTextures(){};
	
	public static boolean CheckAlpha(Minecraft mc)
	{
		TexturePackBase tpBase = mc.texturePackList.selectedTexturePack;
		try
		{
			BufferedImage ter = readTextureImage(tpBase.getResourceAsStream("/terrain.png"));
			int width = ter.getWidth()/16;
			int height = ter.getHeight()/16;
			for(int i = 0; i<width*4; i++)
				for(int j=0; j<height*2; j++)
				{
					Color color = new Color(ter.getRGB(i,j),true);
					if(color.getAlpha()!=0xFF)
					{
						ter.flush();
						return false;
					}
				}
			ter.flush();
		}
		catch (Exception exception)
        {
            exception.printStackTrace();
        }
		return true;
	}
	
	private static BufferedImage readTextureImage(InputStream iStream) throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(iStream);
        iStream.close();
        return bufferedimage;
    }
}
