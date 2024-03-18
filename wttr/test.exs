Mix.install([:dagger])

dag = Dagger.connect!()

dag
|> Dagger.Client.container()
|> Dagger.Container.from("alpine")
|> Dagger.Container.directory("wttr")
|> Dagger.Directory.file("mix.exs")
|> Dagger.File.contents()
|> dbg()

Dagger.close(dag)
