# DefineX-Case
DefineX-Case proje de kullanıcı login olup, ana sayfada ki üç servisten gelen ürünleri görüntüleyebiliyor.

Projeyi kullanabilmek için test@test.com emaili ile 123456 şifresini login olurken kullanmak yeterli başka bir bağımlılık projede mevcut değil.

Projede login olduktan sonra, profil sekmesinden log out olmadığınız sürece app sizin bilgilerinizi hafızasında tutar ve sizi otomatik olarak ana sayfaya yönlendirir.
Eğer internet bağlantınız varsa app servislerden güncel verileri çeker ve bu verileri size gösterirken bir yandan da database'e kayıt eder daha sonrasında eğer internet bağlantınız olmadan app girerseniz app size database'de olan verierli gösterecektir.

Projemde yukarıda belirttiğim login bilgileri dışında bir bağımlılık yoktur.

