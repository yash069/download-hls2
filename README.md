Modification to Christopher A Longo's download-hls.

Downloads, decrypts, and optionally joins HTTP Live Streaming (HLS) transport streams.

http://tools.ietf.org/html/draft-pantos-http-live-streaming

Supports encrypted and plain streams. Added support for locally downloaded playlist and keys

usage: download-hls [options...] <url>  
 -h,--help              print this message.  
 -k,--force-key <key>   force use of the supplied AES-128 key.  
 -o,--output <file>     join all transport streams to one file.  
 -p <playlist>          force local playlist  
 -s,--silent            silent mode.  
 -y                     overwrite output files.  
 
Issues

- Next to no error handling.
- Individual segments will be overwritten irregardless of "-o".
- Has not been tested on all playlists.
