package tp3nicolas.bebetes;

/**
 *
 * @author collet
 * @author fmallet Enl�ve l'h�ritage de JFrame inutile.
 * @version 1.1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LanceBebetes
{

    public static void main (String args[]) {
        final JFrame frame = new JFrame("Champ de bebetes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final ChampDeBebetes champ = new ChampDeBebetes(640,480,20);
        frame.getContentPane().add(champ, BorderLayout.CENTER);

        MouseListener mouseListener = new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println(champ.getNombreDeBebetes());
            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {

            }
        };
        frame.addMouseListener(mouseListener);
        champ.demarre();

        frame.pack();
        frame.setVisible(true);
    }


}
