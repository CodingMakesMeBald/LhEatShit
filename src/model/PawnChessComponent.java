package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent{

    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;


    private Image pawnImage;
    private int counter1;



    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/white-pawn.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/black-pawn.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (this.getChessColor() == ChessColor.BLACK){
            //第一步可以走两格
            if (source.getX() == 1 && destination.getX() - source.getX() == 2 && destination.getY() == source.getY()
                    && chessComponents[source.getX() + 2][source.getY()] instanceof EmptySlotComponent
                    && chessComponents[source.getX() + 1][source.getY()] instanceof EmptySlotComponent){
                chessComponents[destination.getX() - 1][destination.getY()].chessComponent = this;
                return true;
            }
            if(destination.getX() - source.getX() == 1 && destination.getY() == source.getY()
                    && chessComponents[source.getX() + 1][source.getY()] instanceof EmptySlotComponent){
                chessComponents[destination.getX() - 2][destination.getY()].chessComponent = null;
                return true;
            }
            //吃子
            if (destination.getX() - source.getX() == 1 && destination.getY() - source.getY() == -1
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.WHITE) {
                return true;
            }
            if (destination.getX() - source.getX() == 1 && destination.getY() - source.getY() == 1
                    && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.WHITE) {
                return true;
            }


        }else if (this.getChessColor() == ChessColor.WHITE){
                //第一步可以走两格
                if (source.getX() == 6 && destination.getX() - source.getX() == -2 && destination.getY() == source.getY()
                        && chessComponents[source.getX() - 2][source.getY()] instanceof EmptySlotComponent
                        && chessComponents[source.getX() - 1][source.getY()] instanceof EmptySlotComponent) {
                    chessComponents[destination.getX() + 1][destination.getY()].chessComponent = this;
                    return true;
                }
                if (destination.getX() - source.getX() == -1 && destination.getY() == source.getY()
                        && chessComponents[source.getX() - 1][source.getY()] instanceof EmptySlotComponent) {
                    chessComponents[destination.getX() + 2][destination.getY()].chessComponent = null;
                    return true;
                }
                //吃子
                if (destination.getX() - source.getX() == -1 && destination.getY() - source.getY() == -1
                        && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.BLACK) {
                    return true;
                }
                if (destination.getX() - source.getX() == -1 && destination.getY() - source.getY() == 1
                        && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.BLACK) {
                    return true;
                }


        }
        return false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }




}
