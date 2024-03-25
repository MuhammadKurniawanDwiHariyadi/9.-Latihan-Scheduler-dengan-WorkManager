# 9. Latihan Schedeuler dengan WorkManager

Ini adalah proyek latihan dari kursus **Android Fundamental: Background Task & Scheduler** di platform **Dicoding**.

Penggunaan LoopJ di dasari dengan penjelasan berikut <br>
JSON adalah kepanjangan dari JavaScript Object Notation. Ia merupakan format pertukaran data yang ringan, mudah dibaca dan ditulis oleh manusia, serta mudah diterjemahkan dan dibuat (generate) oleh komputer. Ada dua macam JSON, yaitu JSON Array yang ditandai dengan tanda kurung siku / "[]" dan JSON Object yang ditandai dengan kurung kurawal / "{}".

![Image 1](https://github.com/MuhammadKurniawanDwiHariyadi/9.-Latihan-Scheduler-dengan-WorkManager/blob/main/image/image1.png)

Misalnya Anda ingin mengambil data description maka Anda perlu melewati JsonArray weather dulu, kemudian ambil urutan ke-0 dan Anda baru bisa mengambil data description. Contoh untuk kodenya seperti ini: `.getJSONArray("weather").getJSONObject(0).getString("description")` dan jika Anda ingin mengambil data suhu maka kodenya seperti ini: `.getJSONObject("main").getDouble("temp")`. Sehingga, untuk mendapatkan data secara keseluruhan akan seperti ini:

![Image 2](https://github.com/MuhammadKurniawanDwiHariyadi/9.-Latihan-Scheduler-dengan-WorkManager/blob/main/image/image2.png?raw=true)
