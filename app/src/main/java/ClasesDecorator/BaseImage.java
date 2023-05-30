package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;
import com.example.odswix.R;

public class BaseImage implements Image {

    ImageViewAhorcado image;

    public BaseImage(ImageViewAhorcado image) {
            this.image = image;
    }
    @Override
    public void display() {
        image.setImageResource(R.drawable.ahorcado1);
    }
}
