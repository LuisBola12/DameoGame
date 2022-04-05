package dameo.GUI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

/**
 * Esta clase se utiliza para dibujar las imagenes en las posiciones indicadas
 * por el programador
 */
class DrawingImage implements DrawingShape {
    /** Imagen */
    public Image image;
    /** Rectangulo en 2D donde se pone la imagen */
    public Rectangle2D rect;

    public DrawingImage(final Image image, final Rectangle2D rect) {
        this.image = image;
        this.rect = rect;
    }

    @Override
    public boolean contains(final Graphics2D g2, final double x, final double y) {
        return rect.contains(x, y);
    }

    @Override
    public void adjustPosition(final double dx, final double dy) {
        rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());
    }

    @Override
    public void draw(final Graphics2D g2) {
        final Rectangle2D bounds = rect.getBounds2D();
        g2.drawImage(image, (int) bounds.getMinX(), (int) bounds.getMinY(), (int) bounds.getMaxX(),
                (int) bounds.getMaxY(), 0, 0, image.getWidth(null), image.getHeight(null), null);
    }
}