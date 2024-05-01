using Dagger.SDK.GraphQL;

namespace Dagger.SDK.Tests.GraphQL;

public class QueryBuilderTest
{
    [Fact]
    public void TestSelect()
    {
        var query = QueryBuilder
            .Builder()
            .Select("container")
            .Build();

        Assert.Equal("query{container}", query);
    }

    [Fact]
    public void TestSelect_WithArgument()
    {
        var query = QueryBuilder
                        .Builder()
                        .Select("container")
                        .Select("from", [new Argument("address", new StringValue("nginx"))])
                        .Build();

        Assert.Equal("query{container{from(address:\"nginx\")}}", query);

        query = QueryBuilder
                    .Builder()
                    .Select("container")
                    .Select("withExec", [new Argument("args", new ListValue([new StringValue("echo"), new StringValue("hello")]))])
                    .Build();

        Assert.Equal("query{container{withExec(args:[\"echo\",\"hello\"])}}", query);

        query = QueryBuilder
                    .Builder()
                    .Select(
                        "buildDocker",
                        [
                            new Argument(
                                "buildArgs",
                                new ObjectValue(
                                    [
                                        KeyValuePair.Create("key", new StringValue("value") as Value)
                                    ]
                                )
                            )
                        ]
                    )
                    .Build();

        Assert.Equal("query{buildDocker(buildArgs:{key:\"value\"})}", query);

        query = QueryBuilder
                        .Builder()
                        .Select("withEnvVariable", [new Argument("expand", new BooleanValue(true))])
                        .Build();

        Assert.Equal("query{withEnvVariable(expand:true)}", query);
    }

    [Fact]
    public void TestSelect_ImmutableQuery()
    {
        var query1 = QueryBuilder
            .Builder()
            .Select("envVariables");

        var query2 = query1
            .Select("name")
            .Build();

        Assert.Equal("query{envVariables{name}}", query2);

        var query3 = query1
                .Select("value")
                .Build();

        Assert.Equal("query{envVariables{value}}", query3);
    }
}
