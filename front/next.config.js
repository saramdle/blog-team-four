/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  images: {
    domains: ["images.unsplash.com", "img1.daumcdn.net"],
  },
};

module.exports = nextConfig;
