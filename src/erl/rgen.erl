%% Rabbit Module Code Generation
%%
%% Reads record definitions from the distributed rabbit codebase and
%% genreates code capapble of destructuring the erlang/otp binary
%% record results from the JVM side (Clojure or Java).
%%

-module(rgen).
-include("rabbit.hrl").

-export([start/0, stop/0]).

fmt_sym(Syms) ->
    fmt_sym(Syms,"").

fmt_sym([Sym|Tail],Res) ->
    fmt_sym(Tail,Res ++ " " ++ io_lib:format("~p",[Sym]));
fmt_sym([],Res) ->
    Res.

start() ->
    %% FullCommand = init:get_plain_arguments(),
    io:format("'(~n",[]),
    io:format("  (user~s)~n", [fmt_sym(record_info(fields,user))]),
    io:format("  (permission~s)~n",[fmt_sym(record_info(fields,permission))]),
    io:format("  (user_vhost~s)~n",[fmt_sym(record_info(fields,user_vhost))]),
    io:format("  (user_permission~s)~n",[fmt_sym(record_info(fields,user_permission))]),
    io:format("  (vhost~s)~n",[fmt_sym(record_info(fields,vhost))]),
    io:format("  (connection~s)~n",[fmt_sym(record_info(fields,connection))]),
    io:format("  (content~s)~n",[fmt_sym(record_info(fields,content))]),
    io:format("  (resource~s)~n",[fmt_sym(record_info(fields,resource))]),
    io:format("  (exchange~s)~n",[fmt_sym(record_info(fields,exchange))]),
    io:format("  (amqqueue~s)~n",[fmt_sym(record_info(fields,amqqueue))]),
    io:format("  (route~s)~n",[fmt_sym(record_info(fields,route))]),
    io:format("  (reverse_route~s)~n",[fmt_sym(record_info(fields,reverse_route))]),
    io:format("  (binding~s)~n",[fmt_sym(record_info(fields,binding))]),
    io:format("  (reverse_binding~s)~n",[fmt_sym(record_info(fields,reverse_binding))]),
    io:format("  (listener~s)~n",[fmt_sym(record_info(fields,listener))]),
    io:format("  (basic_message~s)~n",[fmt_sym(record_info(fields,basic_message))]),
    io:format(")~n",[]),
    halt(0).
    

stop() ->
    ok.

